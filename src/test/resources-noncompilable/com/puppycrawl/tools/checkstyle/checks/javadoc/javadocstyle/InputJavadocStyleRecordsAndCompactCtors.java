/*
JavadocStyle
scope = (default)private
excludeScope = (default)null
checkFirstSentence = (default)true
endOfSentenceFormat = (default)([.?!][ \t\n\r\f<])|([.?!]$)
checkEmptyJavadoc = (default)false
checkHtml = (default)true
tokens = (default)ANNOTATION_DEF, ANNOTATION_FIELD_DEF, CLASS_DEF, CTOR_DEF, \
         ENUM_CONSTANT_DEF, ENUM_DEF, INTERFACE_DEF, METHOD_DEF, PACKAGE_DEF, \
         VARIABLE_DEF, RECORD_DEF, COMPACT_CTOR_DEF


*/

//non-compiled with javac: Compilable with Java14
package com.puppycrawl.tools.checkstyle.checks.javadoc.javadocstyle;

public class InputJavadocStyleRecordsAndCompactCtors { // ok

    public record MyRecord() { // ok

        /** // violation
         * This Javadoc is missing an ending period
         */
        private static String second;

        /**
         * We don't want {@link com.puppycrawl.tools.checkstyle.checks.JavadocStyleCheck}
         * tags to stop the scan for the end of sentence.
         *
         * @see Something
         */
        public MyRecord() { // ok
        }

        /**
         * This is ok!
         */
        private void method1() { // ok
        }

        /** // violation
         * This should fail even.though.there are embedded periods
         */
        private void method4() {
        }

        // violation 4 lines below
        /**
         * Test HTML in Javadoc comment
         * <dl>
         * <dt><b>
         * <dd>The dt and dd don't require end tags.
         * </dl>
         * </td>
         * <style>this tag isn't supported in Javadoc</style>
         *
         * @param arg1 <code>dummy
         */
        // violation 5 lines above
        // violation 5 lines above
        // violation 4 lines above
        private void method5(int arg1) {
        }
    }

    /**
     * @param a A parameter
     */
    public record MySecondRecord() {
        static String props = "";

        /** // violation
         * Public check should fail</code>
         * should fail <
         */ // violation 2 lines above
        // violation 2 lines above
        public void method8() {
        }
    }

    /**
     *
     */
    public record MyThirdRecord(String myString) { // ok
    }

    public record MyFourthRecord(String myString) { // ok
        /**
         * This Javadoc contains unclosed tag.
         * <code>unclosed 'code' tag<code>
         */
        // violation 2 lines above
        private static void unclosedTag() {
            System.out.println("stuff");
        }

        public MyFourthRecord { // ok
            /**
             * No period at the end of this sentence
             */
            String myOtherString = "mystring"; // ok
        }
    }

    public record MyFifthRecord() { // ok
        /** // violation
         * No period here on compact ctor
         */
        public MyFifthRecord {
        }
    }
}
