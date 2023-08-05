package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.compiler.extensions.shared.compilerExtensionsTestDir
import java.io.File
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.name.CallableId

class FakeFirDeclarationGeneration(session: FirSession) :
    FirDeclarationGenerationExtension(session) {

    override fun getTopLevelCallableIds(): Set<CallableId> {
        val firTxtFile: File =
            compilerExtensionsTestDir
                .apply(File::mkdirs)
                .resolve("fir.txt")
                .apply(File::createNewFile)
        firTxtFile.writeText("FakeFirDeclarationGeneration has been called")
        return super.getTopLevelCallableIds()
    }
}
