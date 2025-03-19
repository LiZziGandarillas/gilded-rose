# Gilded Rose Kata - Refactorización en Java

## Introducción
Este repositorio contiene una solución refactorizada del **Gilded Rose Kata** en Java, aplicando los **principios SOLID** y los **patrones de diseño Strategy, Template Method y Factory Method**.

## Reglas del Negocio
Cada día:
- `sellIn` disminuye en `1`.
- `quality` disminuye en `1`, excepto para ciertos ítems.
- Si `sellIn` es menor a `0`, `quality` disminuye el doble de rápido.
- **Aged Brie** aumenta su `quality` con el tiempo.
- **Sulfuras** no cambia su `quality` ni `sellIn`.
- **Backstage Pass** aumenta `quality`, pero baja a `0` después del evento.
- `quality` nunca es menor que `0` ni mayor que `50`.

## Problemas del Código Original
- **Violación de SRP**: `GildedRose` maneja toda la lógica de negocio.
- **Violación de OCP**: Agregar un nuevo tipo de ítem requiere modificar `GildedRose`.
- **Lógica compleja con condicionales anidados**, difícil de mantener.

## Solución Aplicando SOLID y Patrones de Diseño

### 0. Abstracción
```java
if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
    // do someting
} else if (items[i].name.equals("Aged Brie")) {
    // do something else
} else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
    // do something else
} else {
    // do something else
}
```
```java
if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
    // does nothing
}
else if (items[i].name.equals("Aged Brie")) {
    if (items[i].quality < 50) {
        items[i].quality = items[i].quality + 1;
    }
    items[i].sellIn = items[i].sellIn - 1;
    if (items[i].sellIn < 0) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;
        }
    }
} else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
    if (items[i].quality < 50) {
        items[i].quality = items[i].quality + 1;
        if (items[i].sellIn < 11) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
            }
        }
        if (items[i].sellIn < 6) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
            }
        }
    }
    items[i].sellIn = items[i].sellIn - 1;
    if (items[i].sellIn < 0) {
        items[i].quality = items[i].quality - items[i].quality;
    }
} else {
    if (items[i].quality > 0) {
        items[i].quality = items[i].quality - 1;
    }
    items[i].sellIn = items[i].sellIn - 1;
    if (items[i].sellIn < 0) {
        if (items[i].quality > 0) {
            items[i].quality = items[i].quality - 1;
        }
    }
}
```

### 1. Creación de la interfaz `ItemUpdater`
**Patrón aplicado:** Strategy  
**Principio SOLID:** DIP (Dependency Inversion)
```java
interface ItemUpdater {
    void updateItem(Item item);
}
```

### 2. Creacion de la abtracción `AbstractItemUpdater`
**Patrón aplicado:** Template Method 
**Principio SOLID:** DIP (Dependency Inversion)
```java
abstract class AbstractItemUpdater implements ItemUpdater {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    @Override
    public void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        updateExpired(item);
    }

    protected abstract void updateQuality(Item item);

    protected void updateSellIn(Item item) {
        item.sellIn--;
    }

    protected abstract void updateExpired(Item item);

    protected void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    protected void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }

    protected boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}
```

### 3. Clases concretas para cada tipo de ítem
**Patrón aplicado:** Strategy  
**Principios SOLID:** SRP (Single Responsibility) y LSP (Liskov Substitution)
```java
class StandardItemUpdater extends AbstractItemUpdater {

    @Override
    protected void updateQuality(Item item) {
        decreaseQuality(item);
    }

    @Override
    protected void updateExpired(Item item) {
        if (isExpired(item)) {
            decreaseQuality(item);
        }
    }
}
```
```java
class AgedBrieUpdater extends AbstractItemUpdater {

    @Override
    protected void updateQuality(Item item) {
        increaseQuality(item);
    }

    @Override
    protected void updateExpired(Item item) {
        if (isExpired(item)) {
            increaseQuality(item);
        }
    }
}
```
(Otras clases como `BackstagePassUpdater`, `SulfurasUpdater`, `ConjuredItemUpdater`, siguen la misma idea.)

### 4. Creación de la fábrica `ItemUpdaterFactory`
**Patrón aplicado:** Factory Method  
**Principio SOLID:** OCP (Open/Closed)
```java
class ItemUpdaterFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";

    private final Map<Predicate<Item>, Function<Item, ItemUpdater>> strategies = new LinkedHashMap<>();

    public ItemUpdaterFactory() {
        strategies.put(item -> item.name.equals(AGED_BRIE), item -> new AgedBrieUpdater());
        strategies.put(item -> item.name.equals(BACKSTAGE_PASSES), item -> new BackstagePassUpdater());
        strategies.put(item -> item.name.equals(SULFURAS), item -> new SulfurasUpdater());
        strategies.put(item -> item.name.contains(CONJURED), item -> new ConjuredItemUpdater());
    }

    public ItemUpdater createUpdater(Item item) {
        for (Map.Entry<Predicate<Item>, Function<Item, ItemUpdater>> entry : strategies.entrySet()) {
            if (entry.getKey().test(item)) {
                return entry.getValue().apply(item);
            }
        }
        return new StandardItemUpdater();
    }
}
```

### 5. Integración en `GildedRose`
**Principio SOLID:** DIP (Dependency Inversion)
```java
class GildedRose {
    private final ItemUpdaterFactory updaterFactory;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        this.updaterFactory = new ItemUpdaterFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = updaterFactory.createUpdater(item);
            updater.updateItem(item);
        }
    }
}
```

## Resumen de Aplicación de SOLID y Patrones
| Paso | Patrón de Diseño | Principios SOLID |
|------|----------|------------------|
| Crear `ItemUpdater` | Strategy | DIP |
| Crear `AbstractItemUpdater` | Template Method | DIP |
| Clases para cada ítem | Strategy | SRP, LSP |
| Crear `ItemUpdaterFactory` | Factory Method | OCP |
| Aplicar en `GildedRose` | - | DIP |

## Conclusión
Con esta refactorización:
- `GildedRose` es más **modular, fácil de entender y mantener**.
- Se eliminan las estructuras `if-else` complejas.
- Se aplican los principios **SOLID** correctamente.

