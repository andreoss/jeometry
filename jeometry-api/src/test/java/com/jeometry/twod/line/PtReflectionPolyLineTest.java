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
package com.jeometry.twod.line;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Vect;
import com.aljebra.vector.VectEquals;
import com.jeometry.twod.point.PtReflectionPoint;
import com.jeometry.twod.point.RandomPoint;
import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PtReflectionPolyLine}.
 * @since 0.1
 */
public final class PtReflectionPolyLineTest {

    /**
     * {@link PtReflectionPolyLine} can build the reflection polyline given
     * the reflection center.
     */
    @Test
    public void buildsReflection() {
        final Polyline<Double> input = new PtsPolyline<>(
            Arrays.asList(
                new RandomPoint<>(), new RandomPoint<>(),
                new RandomPoint<>(), new RandomPoint<>()
            )
        );
        final RandomPoint<Double> across = new RandomPoint<>();
        final Polyline<Double> result = new PtReflectionPolyLine<>(across, input);
        for (int idx = 0; idx < result.points().size(); ++idx) {
            PtReflectionPolyLineTest.equal(
                result.points().get(idx),
                PtReflectionPolyLineTest.reflected(
                    across, input.points().get(idx)
                )
            );
        }
    }

    /**
     * {@link PtReflectionPolyLine} can build the reflection polyline
     * across the origin if not given the center.
     */
    @Test
    public void buildsReflectionAcrossOrigin() {
        final Polyline<Double> input = new PtsPolyline<>(
            Arrays.asList(
                new RandomPoint<>(), new RandomPoint<>(),
                new RandomPoint<>(), new RandomPoint<>()
            )
        );
        final Polyline<Double> result = new PtReflectionPolyLine<>(input);
        for (int idx = 0; idx < result.points().size(); ++idx) {
            PtReflectionPolyLineTest.equal(
                result.points().get(idx),
                PtReflectionPolyLineTest.reflected(input.points().get(idx))
            );
        }
    }

    /**
     * Asserts equality of two points.
     * @param first First point
     * @param second Second point
     */
    private static void equal(final Vect<Double> first, final Vect<Double> second) {
        MatcherAssert.assertThat(
            new VectEquals<>(first, second).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * Builds a point-reflected point across a center.
     * @param center Reflection center
     * @param point The point to reflect
     * @param <T> scalar types
     * @return The reflected point
     */
    private static <T> Vect<T> reflected(final Vect<T> center, final Vect<T> point) {
        return new PtReflectionPoint<>(center, point);
    }

    /**
     * Builds a point-reflected point across the origin.
     * @param point The point to reflect
     * @param <T> scalar types
     * @return The reflected point
     */
    private static <T> Vect<T> reflected(final Vect<T> point) {
        return new PtReflectionPoint<>(point);
    }
}
