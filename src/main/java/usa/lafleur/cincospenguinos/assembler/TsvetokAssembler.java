package usa.lafleur.cincospenguinos.assembler;

import java.util.Objects;

public class TsvetokAssembler {
    public static class Tuple<A, B> {
        private A _a;
        private B _b;

        public Tuple(A a, B b) {
            _a = a;
            _b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple<?, ?> tuple = (Tuple<?, ?>) o;
            return _a.equals(tuple._a) &&
                    _b.equals(tuple._b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_a, _b);
        }
    }

    public class TsvetokInstruction {

        public Tuple<Byte, Byte> toBytes() {
            return new Tuple<>((byte) 0, (byte) 0);
        }
    }

    public TsvetokAssembler() {}

    public TsvetokInstruction createInstruction(String noup) {
        return null;
    }
}
