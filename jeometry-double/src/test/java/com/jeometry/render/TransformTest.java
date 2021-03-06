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
package com.jeometry.render;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Vect;
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.twod.point.XyPoint;
import java.awt.Dimension;
import java.awt.Point;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Transform}.
 * @since 0.1
 */
public final class TransformTest {

    /**
     * {@link Transform} transforms DblPoint.
     */
    @Test
    public void transformsDblPoint() {
        final Double xcoor = Math.random();
        final Double ycoor = Math.random();
        final DblPoint point = new DblPoint(xcoor, ycoor);
        final Surface ctx = TransformTest.context();
        final Point result = new Transform(ctx).transform(point);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            result.getX(), Matchers.closeTo(
                (int) (ctx.width() / 2d + ctx.scale() * xcoor
                    - ctx.center().dblx() * ctx.scale()), error
            )
        );
        MatcherAssert.assertThat(
            result.getY(), Matchers.closeTo(
                (int) (ctx.height() / 2d - ctx.scale() * ycoor
                    + ctx.center().dbly() * ctx.scale()), error
            )
        );
    }

    /**
     * {@link Transform} transforms XyPoint.
     */
    @Test
    public void transformsXyPoint() {
        final Double xcoor = Math.random();
        final Double ycoor = Math.random();
        final XyPoint<Double> point = new DblPoint(xcoor, ycoor);
        final Surface ctx = TransformTest.context();
        final Point result = new Transform(ctx).transform(point);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            result.getX(), Matchers.closeTo(
                (int) (ctx.width() / 2d + ctx.scale() * xcoor
                    - ctx.center().dblx() * ctx.scale()), error
            )
        );
        MatcherAssert.assertThat(
            result.getY(), Matchers.closeTo(
                (int) (ctx.height() / 2d - ctx.scale() * ycoor
                    + ctx.center().dbly() * ctx.scale()), error
            )
        );
    }

    /**
     * {@link Transform} transforms vector.
     */
    @Test
    public void transformsVect() {
        final Double xcoor = Math.random();
        final Double ycoor = Math.random();
        final Vect<Double> point = new DblPoint(xcoor, ycoor);
        final Surface ctx = TransformTest.context();
        final Point res = new Transform(ctx, new Decimal()).transform(point);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            res.getX(), Matchers.closeTo(
                (int) (ctx.width() / 2d + ctx.scale() * xcoor
                    - ctx.center().dblx() * ctx.scale()), error
            )
        );
        MatcherAssert.assertThat(
            res.getY(), Matchers.closeTo(
                (int) (ctx.height() / 2d - ctx.scale() * ycoor
                    + ctx.center().dbly() * ctx.scale()), error
            )
        );
    }

    /**
     * {@link Transform} makes inverse transformation.
     */
    @Test
    public void transformsAwtPoint() {
        final int xcoor = 2003;
        final int ycoor = 1956;
        final Surface ctx = TransformTest.context();
        MatcherAssert.assertThat(
            new Transform(ctx).inverse(new Point(xcoor, ycoor)),
            Matchers.equalTo(
                new DblPoint(
                    (xcoor - ctx.width() / 2d) / ctx.scale()
                        + ctx.center().dblx(),
                    (ctx.height() / 2d - ycoor) / ctx.scale()
                        + ctx.center().dbly()
                )
            )
        );
    }

    /**
     * Builds a random awt context.
     * @return A random {@link Surface}
     */
    private static Surface context() {
        final int width = 250;
        final int height = 125;
        return new Surface(
            new Dimension(width, height),
            Math.random(),
            new DblPoint(Math.random(), Math.random())
        );
    }
}
