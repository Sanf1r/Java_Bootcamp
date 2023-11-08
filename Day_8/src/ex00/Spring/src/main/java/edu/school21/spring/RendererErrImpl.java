package edu.school21.spring;

public class RendererErrImpl implements Renderer {

    private PreProcessor pp;

    public RendererErrImpl(PreProcessor pp) {
        this.pp = pp;
    }

    @Override
    public void render(String input) {
        System.err.println(pp.process(input));
    }
}
