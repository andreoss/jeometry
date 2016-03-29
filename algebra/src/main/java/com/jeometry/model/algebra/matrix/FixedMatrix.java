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
package com.jeometry.model.algebra.matrix;

import com.google.common.base.Preconditions;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Dot;
import com.jeometry.model.algebra.vector.FixedVector;
import com.jeometry.model.algebra.vector.Vect;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a matrix defined by fixed coordinates.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString
public class FixedMatrix implements Matrix {

    /**
     * Coordinates.
     */
    private Scalar[] coors;
    
    /**
     * Source vector space dimension.
     */
    private final int source;
    
    /**
     * Target vector space dimension.
     */
    private final int target;

    /**
     * Constructor.
     * @param coor Vector coordinates
     */
    public FixedMatrix(final int lines, final int columns,
        final Scalar... coor) {
        Preconditions.checkArgument(
            lines * columns == coor.length,
            "Expected %d scalars for a matrix with %d lines and %d columns",
            lines * columns, lines, columns
        );
        this.coors = coor;
        this.source = lines;
        this.target = columns;
    }

    /**
     * Modifies a coordinate of the matrix.
     * @param lin Line index of the coordinate to modify (1-based index)
     * @param col Column index of the coordinate to modify (1-based index)
     * @param cor New coordinate
     */
    public final void setCoor(final int lin, final int col, final Scalar cor) {
        this.coors[this.index(lin, col)] = cor;
    }

    @Override
    public final Scalar[] coords() {
        return Arrays.copyOf(this.coors, this.coors.length);
    }

    @Override
    public Scalar[] column(final int i) {
        final int first = this.index(1, i);
        return Arrays.copyOfRange(this.coors, first, first + this.source);
    }

    @Override
    public Scalar[] line(final int j) {
        final int first = this.index(j, 1);
        final Scalar[] result = new Scalar[this.target];
        for (int i = 0; i < this.target; ++i) {
            result[i] = this.coors[first + i * this.source];
        }
        return result;
    }

    @Override
    public Vect apply(final Vect input) {
        final Scalar[] result = new Scalar[this.target];
        for (int i = 0; i < this.target; ++i) {
            result[i] = new Dot(
                input, new FixedVector(this.column(i+1))
            ).value();
        }
        return new FixedVector(result);
    }

    @Override
    public Integer columns() {
        return this.target;
    }

    @Override
    public Integer lines() {
        return this.source;
    }

    /**
     * Returns the single array index based on line and column indices.
     * @param lin Line index (1-based)
     * @param col Column index (1-based)
     * @return The single array index
     */
    private int index(int lin, int col) {
        return this.source * (col - 1) + lin - 1;
    }

}