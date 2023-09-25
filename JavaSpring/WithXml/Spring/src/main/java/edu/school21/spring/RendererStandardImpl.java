package edu.school21.spring;

public class RendererStandardImpl implements Renderer {
    private PreProcessor pp;

    public RendererStandardImpl(PreProcessor pp) {
        this.pp = pp;
    }

    @Override
    public void render(String input) {
        System.out.println(pp.process(input));
    }
}
