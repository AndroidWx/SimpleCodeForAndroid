package com.joye.hk6domain.constants;

import static com.joye.hk6domain.constants.Number.N_1;
import static com.joye.hk6domain.constants.Number.N_10;
import static com.joye.hk6domain.constants.Number.N_11;
import static com.joye.hk6domain.constants.Number.N_12;
import static com.joye.hk6domain.constants.Number.N_13;
import static com.joye.hk6domain.constants.Number.N_14;
import static com.joye.hk6domain.constants.Number.N_15;
import static com.joye.hk6domain.constants.Number.N_16;
import static com.joye.hk6domain.constants.Number.N_17;
import static com.joye.hk6domain.constants.Number.N_18;
import static com.joye.hk6domain.constants.Number.N_19;
import static com.joye.hk6domain.constants.Number.N_2;
import static com.joye.hk6domain.constants.Number.N_20;
import static com.joye.hk6domain.constants.Number.N_21;
import static com.joye.hk6domain.constants.Number.N_22;
import static com.joye.hk6domain.constants.Number.N_23;
import static com.joye.hk6domain.constants.Number.N_24;
import static com.joye.hk6domain.constants.Number.N_25;
import static com.joye.hk6domain.constants.Number.N_26;
import static com.joye.hk6domain.constants.Number.N_27;
import static com.joye.hk6domain.constants.Number.N_28;
import static com.joye.hk6domain.constants.Number.N_29;
import static com.joye.hk6domain.constants.Number.N_3;
import static com.joye.hk6domain.constants.Number.N_30;
import static com.joye.hk6domain.constants.Number.N_31;
import static com.joye.hk6domain.constants.Number.N_32;
import static com.joye.hk6domain.constants.Number.N_33;
import static com.joye.hk6domain.constants.Number.N_34;
import static com.joye.hk6domain.constants.Number.N_35;
import static com.joye.hk6domain.constants.Number.N_36;
import static com.joye.hk6domain.constants.Number.N_37;
import static com.joye.hk6domain.constants.Number.N_38;
import static com.joye.hk6domain.constants.Number.N_39;
import static com.joye.hk6domain.constants.Number.N_4;
import static com.joye.hk6domain.constants.Number.N_40;
import static com.joye.hk6domain.constants.Number.N_41;
import static com.joye.hk6domain.constants.Number.N_42;
import static com.joye.hk6domain.constants.Number.N_43;
import static com.joye.hk6domain.constants.Number.N_44;
import static com.joye.hk6domain.constants.Number.N_45;
import static com.joye.hk6domain.constants.Number.N_46;
import static com.joye.hk6domain.constants.Number.N_47;
import static com.joye.hk6domain.constants.Number.N_48;
import static com.joye.hk6domain.constants.Number.N_49;
import static com.joye.hk6domain.constants.Number.N_5;
import static com.joye.hk6domain.constants.Number.N_6;
import static com.joye.hk6domain.constants.Number.N_7;
import static com.joye.hk6domain.constants.Number.N_8;
import static com.joye.hk6domain.constants.Number.N_9;

/**
 * Created by xiang on 16/10/5.
 */

public enum  CompositeMantissa implements IProbability,INumbers {
    CM0 {
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Mantissa0;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_19,N_28,N_37,N_46};
        }
    },
    CM1 {
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_1,N_10,N_29,N_38,N_47};
        }
    },
    CM2{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_2,N_11,N_20,N_39,N_48 };
        }
    },
    CM3{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_3,N_12,N_21,N_30,N_49 };
        }
    },
    CM4{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_4,N_13,N_22,N_31,N_40 };
        }
    },
    CM5{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_5,N_14,N_23,N_32,N_41 };
        }
    },
    CM6{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_6,N_15,N_24,N_33,N_42 };
        }
    },
    CM7{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_7,N_16,N_25,N_34,N_43 };
        }
    },
    CM8{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_8,N_17,N_26,N_35,N_44};
        }
    },
    CM9{
        @Override
        public double getTheoryProbability() {
            return Probalility.CompositeMantissa.Default;
        }

        @Override
        public int[] getNumbers() {
            return new int[]{N_9,N_18,N_27,N_36,N_45};
        }
    };

}
