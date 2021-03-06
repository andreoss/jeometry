/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2020, Hamdi Douss
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.jeometry.render.awt;

import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.render.Surface;
import com.jeometry.twod.Figure;
import com.jeometry.twod.Shape;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.mock.SpyLine;
import java.awt.Graphics2D;
import java.util.concurrent.CountDownLatch;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Awt}.
 * @since 0.1
 */
public final class AwtTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Awt} becomes visible when rendering a figure.
     */
    @Test
    public void becomesVisible() {
        final Awt awt = new Awt();
        awt.render(new Figure());
        MatcherAssert.assertThat(awt.isVisible(), Matchers.is(true));
    }

    /**
     * {@link Awt} delegates size modifications to drawable surface.
     */
    @Test
    public void modifiesDrawableSurfaceSize() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final Awt awt = new Awt(surface);
        final int width = 1401;
        final int height = 2011;
        awt.render(new Figure());
        awt.withSize(width, height);
        final double scale = surface.context().scale();
        MatcherAssert.assertThat(
            surface.getWidth(), Matchers.equalTo((int) scale * width)
        );
        MatcherAssert.assertThat(
            surface.getHeight(), Matchers.equalTo((int) scale * height)
        );
    }

    /**
     * {@link Awt} adds a painter, and delegates figure rendering
     * to this painter.
     * @throws InterruptedException If fails.
     */
    @Test
    public void addsPainter() throws InterruptedException {
        final Awt awt = new Awt();
        final CountDownLatch latch = new CountDownLatch(1);
        final AbstractAwtPaint<?> painter = new AbstractAwtPaint<Line<Double>>(
            new Decimal(), Line.class
        ) {
            @Override
            protected void draw(final Shape<Line<Double>> renderable,
                final Graphics2D graphic, final Surface ctx) {
                ((Line<?>) renderable.renderable()).direction();
                latch.countDown();
            }
        };
        final SpyLine<Double> line = new SpyLine<>();
        awt.add(painter).render(new Figure().add(line));
        latch.await();
        MatcherAssert.assertThat(line.directioned(), Matchers.equalTo(true));
    }

}
