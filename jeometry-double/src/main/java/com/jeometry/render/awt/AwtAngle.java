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

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.render.Surface;
import com.jeometry.twod.Shape;
import com.jeometry.twod.angle.Angle;
import com.jeometry.twod.ray.PtDirRay;
import java.awt.Graphics2D;

/**
 * Awt Angle painter that draws an angle on an AWT graphics.
 * @since 0.1
 */
public final class AwtAngle extends AbstractAwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtAngle(final Field<Double> field) {
        super(field, Angle.class);
    }

    /**
     * Ctor.
     */
    public AwtAngle() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape<?> renderable, final Graphics2D graphics,
        final Surface context) {
        @SuppressWarnings("unchecked")
        final Angle<Double> angle = (Angle<Double>) renderable.renderable();
        final AwtRay drawer = new AwtRay(this.field());
        drawer.draw(
            new Shape<>(new PtDirRay<>(angle.origin(), angle.start())),
            graphics, context
        );
        drawer.draw(
            new Shape<>(new PtDirRay<>(angle.origin(), angle.end())),
            graphics, context
        );
    }

}
