package uz.pdp.appproblemsolver.compiler;

import uz.pdp.appproblemsolver.common.enums.Lang;

import java.io.IOException;

public interface Compiler {
    public void compile(Lang lang, String code) throws IOException;
}
