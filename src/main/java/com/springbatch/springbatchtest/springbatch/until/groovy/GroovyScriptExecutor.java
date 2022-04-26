package com.springbatch.springbatchtest.springbatch.until.groovy;

public class GroovyScriptExecutor {
//
//    /**
//     * 执行groovy脚本
//     *
//     * @param scriptString 脚本内容
//     * @param inputParam   入参
//     * @return 出参
//     */
//    public static Object execute(String scriptString, Map<String, Object> inputParam) {
//        return execute(GroovyScriptParser.parse(scriptString), inputParam);
//    }
//
//    /**
//     * 执行groovy脚本
//     *
//     * @param script     脚本
//     * @param inputParam 入参
//     * @return 出参
//     */
//    public static <Binding> Object execute(Script script, Map<String, Object> inputParam) {
//        Binding binding = new Binding();
//        inputParam.forEach(binding::setVariable);
//        script.setBinding(binding);
//        return script.run();
//    }
//
//    /**
//     * 执行groovy脚本
//     *
//     * @param scriptString 脚本内容
//     * @param inputParam   入参
//     * @param output       重定向stdout
//     * @return 出参
//     */
//    public static Object execute(String scriptString, Map<String, Object> inputParam, OutputStream output) {
//        return execute(GroovyScriptParser.parse(scriptString), inputParam, output);
//
//    }
//
//    /**
//     * 执行groovy脚本
//     *
//     * @param script     脚本
//     * @param inputParam 入参
//     * @param output     重定向stdout
//     * @return 出参
//     */
//    public static Object execute(Script script, Map<String, Object> inputParam, OutputStream output) {
//        Binding binding = new Binding();
//        inputParam.forEach(binding::setVariable);
//        binding.setProperty("out", new PrintStream(output));
//        script.setBinding(binding);
//        return script.run();
//    }
}
