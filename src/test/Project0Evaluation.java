package test;

public class Project0Evaluation {
    public static void main(String[] args) {
        int totalTests = 17;

        System.out.println("BEGIN PROJECT 0 EVALUATION");
        System.out.println("Total tests: " + totalTests);
        System.out.println("==========================");
        System.out.println();

        int testsRun = 0;
        int testsPassed = 0;
        boolean allPassed = true;

        TestRunner.TestResults helloResult = TestRunner.runTest(HelloTest.class);
        System.out.println(helloResult.toString());
        allPassed = allPassed && helloResult.allPassed();

        testsRun += helloResult.testCount;
        testsPassed += helloResult.successCount;

        TestRunner.TestResults rangeResult = TestRunner.runTest(RangeTest.class);
        System.out.println(rangeResult.toString());
        allPassed = allPassed && rangeResult.allPassed();

        testsRun += rangeResult.testCount;
        testsPassed += rangeResult.successCount;

        if (testsRun < totalTests) {
            System.out.printf("Note: %d tests were excluded from evaluation.\n", totalTests - testsRun);
        }

        if (testsRun == totalTests && allPassed) {
            System.out.println("!!! ALL TESTS PASSED! GREAT JOB !!!");
        }

        System.out.printf("Tests Passed: [%d / %d]\n", testsPassed, testsRun);
        System.out.printf("Overall Project Score: %d%%\n", Math.round(Math.floor(((double)(testsPassed) / (double)(totalTests)) * 100)));
    }
}
