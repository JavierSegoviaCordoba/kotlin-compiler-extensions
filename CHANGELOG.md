# Changelog

## [Unreleased]

### Added

- `IrValueParameter.createIrField(): IrField`
- `IrPluginContext.createIrField(): IrField`
- `IrDeclaration.toIrExpression(): IrExpression`
- `IrPluginContext.createIrBlockBody(): IrBlockBody`
- `isSubtypeOf(): Boolean`
- `IrPluginContext.irType(): IrType`
- `KFunction<*>.toName(): Name`
- `FirBasedSymbol<*>.contextReceivers: List<FirContextReceiver>`
- `FirBasedSymbol<*>.name: Name`
- `IrElement.contextReceivers: List<IrValueParameter>`
- `IrDeclaration.toIrCall(): IrCall`
- `Sequence<IrTreeNode>.filterIrIsInstance(): Sequence<T>`
- `IrElement.irType: IrType`
- `IrType.buildVariable(): IrVariable`
- `buildVariable(): IrVariable`
- `ConeKotlinType.toValueParameter(): FirValueParameter`
- `FirSession.coneKotlinType: ConeKotlinType`
- `FirBasedSymbol<*>.coneKotlinType: ConeKotlinType?`
- `FirClassSymbol<*>.toPrimaryConstructor(): FirConstructor`
- `ConeTypeProjection.toFirTypeParameter(): FirTypeParameterRef`
- `FirTypeRef.toValueParameter(): FirValueParameter`
- `ClassId.toFirTypeRef(): FirTypeRef`
- `FirClassLikeSymbol<*>.toFirTypeRef(): FirTypeRef`
- `FirFunctionSymbol<*>.contextReceiversToValueParameters(): List<FirValueParameter>`
- `ClassId.toConeType(): ConeClassLikeType`
- and many more...

### Changed

- `FirBasedSymbol<*>.packageFqName()` -> `FirBasedSymbol<*>.packageFqName`
- `buildVariable` to `createIrVariable`
- Kotlin version to `1.9.255-SNAPSHOT`
- `irCall` extensions

### Deprecated

### Removed

### Fixed

- Box tests don't check IR

### Updated

- `org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin -> 1.9.20-station-823`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.5.0-SNAPSHOT`
- `gradle -> 8.2.1`

## [0.1.1-alpha.4] - 2023-06-13

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

[Unreleased]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.4...HEAD

[0.1.1-alpha.4]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.3...0.1.1-alpha.4

[0.1.1-alpha.3]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.2...0.1.1-alpha.3

[0.1.1-alpha.2]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.1...0.1.1-alpha.2

[0.1.1-alpha.1]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/commits/0.1.1-alpha.1
