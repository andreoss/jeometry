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
package com.aljebra.scalar.condition;

import com.aljebra.field.Field;
import com.aljebra.field.mock.MkField;
import com.aljebra.scalar.Scalar;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Equals}.
 * @since 0.1
 */
public final class EqualsTest {

    /**
     * {@link Equals} resolves to true if scalars are equal.
     */
    @Test
    public void resolvesToTrueWhenEqual() {
        final Object obj = new Object();
        final Scalar<Object> first = new Scalar.Default<>(obj);
        final Scalar<Object> second = new Scalar.Default<>(obj);
        final Field<Object> field = new MkField<>(new Object(), new Object());
        MatcherAssert.assertThat(
            new Equals<>(first, second).resolve(field),
            Matchers.is(true)
        );
    }

    /**
     * {@link Equals} resolves to false if scalars are not equal.
     */
    @Test
    public void resolvesToFalseWhenNotEqual() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final Field<Object> field = new MkField<>(new Object(), new Object());
        MatcherAssert.assertThat(
            new Equals<>(first, second).resolve(field),
            Matchers.is(false)
        );
    }

}
