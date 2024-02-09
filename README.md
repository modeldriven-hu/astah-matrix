# Astah Matrix Plug-in

This enables you to display dependencies among model elements
in Astah SysML, but might also work with other Astah variants
as well.

# Screenshot

![image](https://github.com/modeldriven-hu/astah-matrix/assets/8182138/48822ee5-08d6-433c-a3f6-e3d93fb0eef5)

# Supported functions

- Select model type for rows and columns
- Select package for rows and columns
- Select dependency type
- Display dependency matrix (row to column, column to row, both)
- Ability to select a single cell and highlight both row and column
- Context menu support including:
    - Selecting row or column element in tree
    - Creating relationship between elements
    - Hiding selected rows and columns
    - Show all hidden rows and columns
- Display elements based on stereotype

# Build

Use the astah-build command to build the plugin

# Extension points

- To add a new element type extend ElementTypeSelectorData.java
- To add a new dependency type extend DependencyTypeSelectorData.java

