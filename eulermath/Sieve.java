package eulermath;

import eulermath.LongModPower;

/**
 * Implement a version of the Sieve of Eratosthenes with the following
 * optimizations:
 * <p>
 * <p><ul>
 * <li>only 8 out of every 30 integers are considered.   This saves on
 * storage and allows the sieve to be larger.  Numbers divisible by 2, 3
 * and 5 are already eliminated.
 * <li>When the table is filled, a new table of the next 90 million integers
 * is started using the same space.
 * <li>A buffer of 10 items is used to hold upcoming values.  This is
 * intialized with 2, 3, and 5 and avoids requiring that those numbers be
 * checked in later interations through the loop.  This buffer also helps
 * handle the transition from the end of one table to the reinitialization
 * and setting of the next table.
 * </ul><p>
 *
 * @author Warren Usui
 */
public class Sieve {
    private final static int[] pindex = new int[]
            {1, 7, 11, 13, 17, 19, 23, 29};
    private final LongModPower longmodobj = new LongModPower();
    private final int sievesz = (int)(longmodobj.raise(10L, 6L) * 9L);
    private final int tblsize = (sievesz/30) * 8;
    private boolean table[];
    private int divlist[];
    private int start_pt;
    private int tbl_indx;
    private int prm_indx;
    private int out_buffer[];
    private int obuf_ind;
    private int nb_index;
    private int maxprime;
    /**
     * When intializing the table, the prime values less than the square
     * root of size are stored in divlist.  The sieve is created and the
     * first three elements of the buffer (2, 3, and 5) are added.
     *
     * @param size maximum prime number value for this problem.
     */
    public Sieve(int size) {
        divlist = new int[(size > longmodobj.raise(10L, 8L))
                ? 10000 : 2000];
        table = new boolean[tblsize];
        table[0] = true;
        start_pt = 0;
        tbl_indx = 1;
        prm_indx = 0;
        maxprime = size;
        do_first_sieve();
        out_buffer = new int[10];
        obuf_ind = 3;
        out_buffer[0] = 2;
        out_buffer[1] = 3;
        out_buffer[2] = 5;
        tbl_indx = 1;
        nb_index = 10;
    }
    /**
     * First pass through the sieve.  This differs from later passes
     * in which case the sieve square represents the corresponding
     * square + start_pt.  This pass also establishes the values in
     * divlist, and uses the open prime spaces for dividing values
     * in the prime rather than the values saved in divlist.
     *
     */
    private void do_first_sieve() {
        while (tbl_indx < tblsize) {
            if (! table[tbl_indx]) {
                int nval = unconv(tbl_indx);
                int strt_srch = nval * nval;
                if (strt_srch >= sievesz) {
                    break;
                }
                divlist[prm_indx] = nval;
                prm_indx++;
                while (strt_srch < sievesz) {
                    int tindx = conv(strt_srch);
                    if (tindx > 0) {
                        table[tindx] = true;
                    }
                    strt_srch += 2 * nval;
                }
            }
            tbl_indx++;
        }
        int pnum = divlist[prm_indx-1];
        int stp = conv(pnum) + 1;
        while (pnum * pnum < maxprime) {
            while (table[stp]) {
                stp++;
            }
            pnum = unconv(stp);
            divlist[prm_indx] = pnum;
            prm_indx++;
            stp++;
        }
        divlist[prm_indx] = 0;
        return;
    }
    /**
     * If the end of the table is reached, a new sieve is started for
     * the next (higher) set of numbers.  This sieve relies on the
     * values found in divlist rather than finding the primes in the
     * sieve.
     */
    private void do_later_sieve() {
        for (int i=0; i<tblsize; i++) {
            table[i] = false;
        }
        for (int i=0; i<divlist.length; i++) {
            if (divlist[i] == 0) {
                break;
            }
            int sp = divlist[i] - (start_pt % divlist[i]);
            int spconv = conv(sp);
            while (spconv == -1) {
                sp += divlist[i];
                spconv = conv(sp);
            }
            int step = divlist[i] * 2;
            while (sp < sievesz) {
                spconv = conv(sp);
                if (spconv != -1) {
                    table[spconv] = true;
                }
                sp += step;
            }
        }
        obuf_ind = 0;
        nb_index = 0;
        tbl_indx = 0;
    }
    /**
     * Convert an integer into the index of the number in the sieve
     * represented by table
     *
     * @param number index into the table
     * @return real number value
     */
    private final static int conv(int number) {
        int row = number / 30; 
        int indx = number % 30;
        int col;
        for (col=0; col<pindex.length;  col++) {
            if (pindex[col] == indx) {
                break;
            }
        }
        if (col == pindex.length) {
            return -1;
        }
        return row*8 + col;
    }
    /**
     * Convert an index in sieve into the corresponding integer.
     *
     * @param number index in table
     * @return corresponding number
     */
    private final static int unconv(int number) {
        int row = number / 8;
        int col = pindex[number % 8];
        return row * 30 + col;
    }
    /**
     * If the out_buffer is filled, fill it with the next 10 prime numbers.
     *
     */
    private void get_next_block() {
        while (obuf_ind < 10) {
            if (tbl_indx >= tblsize) {
                break;
            }
            while (table[tbl_indx]) {
                tbl_indx++;
                if (tbl_indx >= tblsize) {
                    out_buffer[obuf_ind] = 0;
                    nb_index = 0;
                    return;
                }
            }
            out_buffer[obuf_ind] = unconv(tbl_indx) + start_pt;
            tbl_indx++;
            obuf_ind++;
            if (tbl_indx >= tblsize) {
                if (obuf_ind < 10) {
                    out_buffer[obuf_ind] = 0;
                    nb_index = 0;
                    return;
                }
            }
        }
        obuf_ind = 0;
        nb_index = 0;
        return;
    }
    /**
     * Look through the buffer of primes and find the next prime.
     * If the buffer is empty, refill it, getting the next set of
     * table entries if needed.
     *
     * @return next prime number available.
     */
    public int get_next() {
        if (nb_index >= 10) {
            get_next_block();
        }
        int retv = out_buffer[nb_index];
        nb_index++;
        if (retv > maxprime) {
            return 0;
        }
        if (retv == 0) {
            start_pt += sievesz;
            if (start_pt < maxprime) {
                do_later_sieve();
                get_next_block();
                retv = out_buffer[nb_index];
                nb_index++;
            }
        }
        return retv;
    }
}
