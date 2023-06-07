package uz.pdp.appproblemsolver.compiler;

import uz.pdp.appproblemsolver.common.enums.Lang;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;

public class CompilerImpl implements Compiler {
    @Override
    public void compile(Lang lang, String code) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.getTask();


    }
}
