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
package com.jeometry.geometry.twod.angle;

import com.jeometry.model.algebra.vector.Minus;
import com.jeometry.model.algebra.vector.Vect;

/**
 * An angle defined by three points.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ThreePtsAngle implements Angle {

    /**
     * Angle origin.
     */
    private Vect org;

    /**
     * Point belonging to the starting ray. The starting ray is defined by
     * the angle origin as its origin and this point as a point in the ray.
     */
    private Vect frst;

    /**
     * Point belonging to the ending ray. The ending ray is defined by
     * the angle origin as its origin and this point as a point in the ray.
     */
    private Vect scnd;

    /**
     * Constructor.
     * @param direction Ray direction
     * @param point Ray origin
     */
    public ThreePtsAngle(final Vect origin, final Vect first,
        final Vect second) {
        this.org = origin;
        this.frst = first;
        this.scnd = second;
    }

    @Override
    public Vect origin() {
        return this.org;
    }

    /**
     * Modifies the point that should belong to the line.
     * @param point New point to pass by.
     */
    public void setOrigin(final Vect point) {
        this.org = point;
    }

    @Override
    public Vect start() {
        return new Minus(this.frst, this.org);
    }

    @Override
    public Vect end() {
        return new Minus(this.scnd, this.org);
    }
}
