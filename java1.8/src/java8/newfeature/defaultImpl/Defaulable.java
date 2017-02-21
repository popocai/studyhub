/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package java8.newfeature.defaultImpl;

import java.util.function.Supplier;

interface HaveDefaultImpl {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default String notRequired() {
        return "Default implementation";
    }

    static HaveDefaultImpl create(Supplier<HaveDefaultImpl> supplier) {
        return supplier.get();
    }
}


public class Defaulable {
    private static class DefaultableImpl implements HaveDefaultImpl {
    }

    private static class OverridableImpl implements HaveDefaultImpl {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    public static void main(String[] args) {
        System.out.println(new DefaultableImpl().notRequired());
        System.out.println(new OverridableImpl().notRequired());

        HaveDefaultImpl defaulable = HaveDefaultImpl.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = HaveDefaultImpl.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());
    }
}