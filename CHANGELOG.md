# Changelog

## [Unreleased]

### Added

### Changed

### Deprecated

### Fixed

### Removed

### Updated

- `gradle -> 8.13`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.8.7`
- `com.javiersc.hubdle:hubdle-version-catalog -> 0.4.7`

## [0.5.2+2.1.10] - 2025-01-27

### Updated

- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.8.2`
- `com.javiersc.hubdle:hubdle-version-catalog -> 0.4.2`
- `org.jetbrains.kotlin:kotlin-gradle-plugin -> 2.1.10`
- `gradle -> 8.12.1`

## [0.5.1+2.1.0] - 2024-11-28

### Updated

- `com.javiersc.hubdle:hubdle-version-catalog -> 0.4.0`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.8.0`

## [0.5.0+2.1.0] - 2024-11-27

### Updated

- `org.jetbrains.kotlin:kotlin-gradle-plugin -> 2.1.0`
- `gradle -> 8.11.1`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.7.18`
- `com.javiersc.hubdle:hubdle-version-catalog -> 0.3.13`

## [0.4.4+2.0.21] - 2024-10-10

### Updated

- `com.javiersc.hubdle:hubdle-version-catalog -> 0.3.12`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.7.14`
- `gradle -> 8.10.2`

## [0.4.3+2.0.20] - 2024-09-08

### Fixed

- `compilerClasspath` configuration not working properly on Windows

## [0.4.2+2.0.20] - 2024-09-03

### Fixed

- `:` separator not supported in `compilerClasspath`

### Updated

- `com.javiersc.hubdle:hubdle-version-catalog -> 0.3.8`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.7.10`

## [0.4.1+2.0.20] - 2024-09-02

### Fixed

- `compilerClasspath` does not support `String`

## [0.4.0+2.0.20] - 2024-09-02

### Added

- `kotlin-compiler-gradle-extensions` project

### Changed

- remove all functions that use context receivers
- all functions to be `inline`

### Updated

- `com.javiersc.hubdle:hubdle-version-catalog -> 0.3.7`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.7.8`

## [0.3.0+2.0.20] - 2024-08-22

### Updated

- `com.javiersc.hubdle:hubdle-version-catalog -> 0.3.6`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.7.7`

## [0.2.0+2.0.10] - 2024-08-16

### Added

- allow disabling logging in `MetaRuntimeClasspathProvider`

### Updated

- `gradle -> 8.10`
- `com.javiersc.hubdle:hubdle-version-catalog -> 0.3.2`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.7.5`

## [0.1.1+2.0.0-RC1] - 2024-04-09

### Added

- `FULL_JDK` and Context Receivers to test configuration
- `IrPluginContext.declarationIrBuilder: DeclarationIrBuilder`
- `IrVariable.toIrGetValue(): IrGetValue`
- `FirBasedSymbol<*>.valueParameters`
- `nothingFirAnonymousFunctionExpression`
- multiple safe FIR functions based on getting nullable types

### Changed

- `FirConstExpression` to `FirLiteralExpression`

### Fixed

- avoid throwing exceptions in some FIR functions

### Updated

- `com.javiersc.hubdle:hubdle-version-catalog -> 0.2.6+2.0.0-RC1`
- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.6.6+2.0.0-RC1`
- `gradle -> 8.7`

## [0.1.1-alpha.5] - 2023-09-29

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

### Fixed

- Box tests don't check IR

### Updated

- `com.javiersc.hubdle:com.javiersc.hubdle.gradle.plugin -> 0.5.0+1.9.30-dev-2548-SNAPSHOT`
- `gradle -> 8.3`

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

[Unreleased]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.5.2+2.1.10...HEAD

[0.5.2+2.1.10]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.5.1+2.1.0...0.5.2+2.1.10

[0.5.1+2.1.0]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.5.0+2.1.0...0.5.1+2.1.0

[0.5.0+2.1.0]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.4.4+2.0.21...0.5.0+2.1.0

[0.4.4+2.0.21]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.4.3+2.0.20...0.4.4+2.0.21

[0.4.3+2.0.20]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.4.2+2.0.20...0.4.3+2.0.20

[0.4.2+2.0.20]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.4.1+2.0.20...0.4.2+2.0.20

[0.4.1+2.0.20]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.4.0+2.0.20...0.4.1+2.0.20

[0.4.0+2.0.20]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.3.0+2.0.20...0.4.0+2.0.20

[0.3.0+2.0.20]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.2.0+2.0.10...0.3.0+2.0.20

[0.2.0+2.0.10]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1+2.0.0-RC1...0.2.0+2.0.10

[0.1.1+2.0.0-RC1]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.5...0.1.1+2.0.0-RC1

[0.1.1-alpha.5]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.4...0.1.1-alpha.5

[0.1.1-alpha.4]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.3...0.1.1-alpha.4

[0.1.1-alpha.3]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.2...0.1.1-alpha.3

[0.1.1-alpha.2]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/compare/0.1.1-alpha.1...0.1.1-alpha.2

[0.1.1-alpha.1]: https://github.com/JavierSegoviaCordoba/kotlin-compiler-extensions/commits/0.1.1-alpha.1
