package org.hasan;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplicationTest {
    @Test
    public void testGetStatus() {
        Application app = new Application();
        assertThat(app.getStatus(), is("OK"));
    }
}
