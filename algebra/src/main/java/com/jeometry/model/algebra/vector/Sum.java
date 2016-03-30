/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
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
package com.jeometry.model.algebra.vector;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Scalar;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A vector represented as the sum of a set of vectors.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString
public final class Sum implements Vect {

    /**
     * Sum operands.
     */
    private final Multiset<Vect> operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    public Sum(final Vect... operands) {
        this.operands = HashMultiset.create(Arrays.asList(operands));
    }

    @Override
    public Scalar[] coords() {
        final int dim = this.operands.iterator().next().coords().length;
        final Scalar[] result = new Scalar[dim];
        for (int axis = 0; axis < dim; ++axis) {
            final Scalar[] coor = new Scalar[this.operands.size()];
            int idx = 0;
            for (Vect oper : this.operands) {
                coor[idx] = oper.coords()[axis];
                ++idx;
            }
            result[axis] = new Add(coor);
        }
        return result;
    }
}
