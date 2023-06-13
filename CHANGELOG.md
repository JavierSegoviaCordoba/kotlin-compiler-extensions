# Changelog

## [Unreleased]

### Added

- `FirBasedSymbol<*>.isProperty(): Boolean`
- `FirBasedSymbol<*>.isFunction(): Boolean`
- `FirBasedSymbol<*>.isTopLevel: Boolean`
- `FirBasedSymbol<*>.isNested: Boolean`
- `FirBasedSymbol<*>.hasOwner: Boolean`
- `<reified T> classId(): ClassId`
- `ClassId.toCallableId(): CallableId`
- `FirContextReceiver.typeRefName: Name?`
- `FirSession.nothingFirFunction(): FirFunction`
- `FirSession.nothingFirFunctionCall: FirFunctionCall`
- `FirContextReceiver.toValueParameter(): FirValueParameter`
- `<reified T : Annotation> IrElement.hasAnnotation(): Boolean`
- `List<FirElement?>.render(): String`
- `FirBasedSymbol<*>.packageFqName(): FqName?`
- `asFirSymbol`
- `FirMemberDeclaration.name: Name`
- `FirTreeNode`
- `IrFunction.contextReceivers: List<IrValueParameter>`

### Changed

- `String.toName(): Name` package to `com.javiersc.kotlin.compiler.extensions.common`
- `String.toFqName(): FqName` package to `com.javiersc.kotlin.compiler.extensions.common`
- `fqName(): FqName` package to `com.javiersc.kotlin.compiler.extensions.common`
- `packageFqName(): FqName` package to `com.javiersc.kotlin.compiler.extensions.common`
- `KClass<*>.toFqName(): FqName` package to `com.javiersc.kotlin.compiler.extensions.common`
- `String.toCallableId(): CallableId` package to `com.javiersc.kotlin.compiler.extensions.common`
- `FqName.toCallableId(): CallableId` package to `com.javiersc.kotlin.compiler.extensions.common`

### Deprecated

### Removed

### Fixed

### Updated

- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.5.0-alpha.31`

## [0.1.1-alpha.3] - 2023-06-08

### Added

- `IrClassExhaustiveKind`
- `IrClass.exhaustiveKind: IrClassExhaustiveKind`
- `IrTreeNode.irFile: IrFile?`
- `IrTreeNode.parentIrClass: IrClass?`
- `IrTreeNode.parentIrDeclaration: IrDeclaration?`
- `IrTreeNode.parentIrDeclarationWithName: IrDeclarationWithName?`
- `IrTreeNode.parentIrFunction: IrFunction?`

### Removed

- `IrTreeNode.fileName: String`
- `IrTreeNode.className: String`
- `IrTreeNode.functionName: String`

### Updated

- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.5.0-alpha.29`

## [0.1.1-alpha.2] - 2023-06-08

### Added

- `IrFunctionExpression.irReturn: IrReturn?`
- `IrFunction.irReturn: IrReturn?`

### Changed

- `IrFunction.buildIrReturn` to `IrFunction.copyIrReturn`

## [0.1.1-alpha.1] - 2023-06-07

### Added

- ir `annotations` extensions
- ir `callableId` extensions
- ir `fqName` extensions
- ir `irCall` extensions
- ir `irClass` extensions
- ir `irClassId` extensions
- ir `irConst` extensions
- ir `irElement` extensions
- ir `irFunction` extensions
- ir `irGeneration` extensions
- ir `irGetEnumValue` extensions
- ir `irReturn` extensions
- ir `irTreeNode` extensions
- ir `irType` extensions
- ir `irTypeArgument` extensions
- ir `irValueParameter` extensions
- ir `name` extensions
- ir `aIr` extensions

### Changed

- `kotlin-compiler-test` to `kotlin-compiler-extensions-test`

[Unreleased]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.3...HEAD

[0.1.1-alpha.3]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.2...0.1.1-alpha.3

[0.1.1-alpha.2]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.1...0.1.1-alpha.2

[0.1.1-alpha.1]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/commits/0.1.1-alpha.1
