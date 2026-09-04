# KitsTest

A Spigot 1.8.8 plugin which allows developer to create custom items which trigger action on player left or right click event

## Adding a new custom item

The custom item system is built so that adding a new item never requires touching the
listener, registry, or main plugin class — you only ever add one new class.

1. **Create a new class under `item/impl/`** that extends `CustomItem`:

   ```java
   package org.diploblastic.kitsTest.item.impl;

   import org.bukkit.ChatColor;
   import org.bukkit.Material;
   import org.bukkit.entity.Player;
   import org.diploblastic.kitsTest.item.CustomItem;

   public class GhastTear extends CustomItem {

       public static final String ID = "ghasttear";
       private static final String DISPLAY_NAME = ChatColor.LIGHT_PURPLE + "Tear of Sorrow";

       public GhastTear() {
           super(ID, DISPLAY_NAME, Material.GHAST_TEAR);
       }

       @Override
       public void onRightClick(Player player) {
           // define right-click behaviour here
       }

       @Override
       public void onLeftClick(Player player) {
           // define left-click behaviour here
       }
   }
   ```

   - `ID` — a plain, lowercase, unique key (no spaces/color codes). Used for the
     `/customitem <id>` command and its tab-completion.
   - `DISPLAY_NAME` — the colored name shown on the physical item. This is also what
     the plugin uses internally to recognise the item on click, so it should be unique
     across all custom items.
   - `Material` — the vanilla item this custom item is based on.
   - Pass all three into `super(ID, DISPLAY_NAME, Material)` from the constructor.

2. **Override `onRightClick` and `onLeftClick`.** Both are abstract on `CustomItem`, so
   every custom item must implement both — even if one is intentionally left empty (see
   `Feather`/`Lilypad`, whose `onLeftClick` does nothing).

3. **Register an instance in `KitsTest#onEnable`:**

   ```java
   registry.register(new GhastTear());
   ```

   That's the only change needed outside the new class itself. The item is now:
   - obtainable via `/customitem ghasttear` (auto-added to tab-complete),
   - clickable, with `onRightClick`/`onLeftClick` firing automatically through
     `CustomItemListener`.

## Why no other file needs to change

`CustomItemListener` only ever refers to items through the abstract `CustomItem` type:

```java
customItem.onRightClick(player);
```

At runtime, Java dispatches this call to whichever concrete subclass the object
actually is (`Feather`, `Lilypad`, `GhastTear`, ...) — this is polymorphism doing the
work. The listener doesn't know or care which custom item it's holding; it just calls
the method, and the correct behaviour runs. That's what makes the system extensible
without editing existing code.

## Build & test loop

1. `mvn clean package` → shaded jar in `target/`.
2. Copy the jar into your test server's `plugins/` folder.
3. Start the server, run `/customitem <id>` to obtain the item, then click it.
