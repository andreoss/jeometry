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
import com.aljebra.scalar.Scalar;

/**
 * A predicate to determine if two scalars are equals.
 * @since 0.1
 */
public final class Equals implements Predicate {

    /**
     * First Scalar.
     */
    @SuppressWarnings("rawtypes")
    private final Scalar first;

    /**
     * Second Scalar.
     */
    @SuppressWarnings("rawtypes")
    private final Scalar second;

    /**
     * Constructor.
     * @param first First scalar
     * @param second Second scalar
     * @param <T> scalar types
     */
    public <T> Equals(final Scalar<T> first, final Scalar<T> second) {
        this.first = first;
        this.second = second;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> boolean resolve(final Field<T> field) {
        return field.equals(this.first, this.second);
    }

}
