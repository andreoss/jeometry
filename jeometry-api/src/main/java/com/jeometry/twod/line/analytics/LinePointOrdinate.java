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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.Field;
import com.aljebra.scalar.Add;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.Line;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A scalar representing the ordinate of a line belonging point,
 * given its abscissa. If the line is vertical, this scalar will be random.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class LinePointOrdinate<T> implements Scalar<T> {

    /**
     * Line for which to calculate ordinate.
     */
    private final Line<T> line;

    /**
     * Point abscissa.
     */
    private final Scalar<T> abscissa;

    /**
     * Constructor.
     * @param line Line for which to calculate ordinate
     * @param abscissa Point abscissa
     */
    public LinePointOrdinate(final Line<T> line, final Scalar<T> abscissa) {
        this.line = line;
        this.abscissa = abscissa;
    }

    @Override
    public T value(final Field<T> field) {
        final boolean inline = field.equals(
            this.line.point().coords()[0], this.abscissa
        );
        final boolean vertical = new Vertical<>(this.line).resolve(field);
        if (vertical && !inline) {
            throw new IllegalStateException(
                "Vertical line could not pass by a point with this abscissa"
            );
        }
        final T result;
        if (vertical && inline) {
            result = field.actual(new Random<T>());
        } else {
            result = field.actual(
                new Add<>(
                    new Multiplication<T>(
                        new Slope<T>(this.line), this.abscissa
                    ),
                    new Intercept<T>(this.line)
                )
            );
        }
        return result;
    }

}
