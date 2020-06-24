package page;

public class Context {
    private final StrategyBox strategy;

    public Context(String devise) {
        if ("mobile".equals(devise)) {
            this.strategy = new BoxMobilePage();
        } else {
            this.strategy = new BoxWebPage();
        }
    }

    public StrategyBox getStrategy() {
        return this.strategy;
    }
}
