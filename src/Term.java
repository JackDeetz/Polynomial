import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Term implements Comparable

{
    private int mExponent ;
    private int mCoefficient ;

    public Term()
    {
        setAll(1,1) ;

    } ;

    public Term(Term other)
    {
        mCoefficient = other.mCoefficient ;
        mExponent = other.mExponent ;

    }
    public Term(String other)
    {
        int coefficient, exponent ;
        if (other.isEmpty())
        {
            mCoefficient = 0 ;
            mExponent = 0 ;
            return ;
        }
        if (other.contains("x"))
        {
            String[] termStringSplit = other.split("x");
            if (termStringSplit[0].length() == 1)
            {
                if (termStringSplit[0].charAt(0) == '+')
                    coefficient = 1;
                else
                    coefficient = -1;
            }
            else
                coefficient = parseInt(termStringSplit[0]) ;

            if (termStringSplit.length == 2)
            {
                exponent = parseInt(termStringSplit[1].substring(1, termStringSplit[1].length())) ;
            }
            else
            {
                exponent = 1 ;
            }

            mExponent = exponent ;
            mCoefficient = coefficient ;
        }
        else
        {
            if (other.charAt(0) == '+')
                coefficient = parseInt(other) ;
            else
                coefficient = parseInt(other) ;

            exponent = 0 ;
            mExponent = exponent ;
            mCoefficient = coefficient ;
        }
    }

    public Term(int coefficient, int exponent)
    {
        mExponent = exponent ;
        mCoefficient = coefficient ;
    }
    public Term clone()
    {
        Term temp = new Term() ;
        temp.setAll(this.mCoefficient, this.mExponent);
        return temp;
    }

    public void setAll(int coefficient, int exponent)
    {
        mExponent = exponent ;
        mCoefficient = coefficient ;
    }



    public int getExponent() {
        return mExponent;
    }

    public void setExponent(int mExponent) {
        this.mExponent = mExponent;
    }

    public int getCoefficient() {
        return mCoefficient;
    }

    public void setCoefficient(int mCoefficient) {
        this.mCoefficient = mCoefficient;
    }

    @Override
    public String toString()
    {
        if (mCoefficient == 0)
        {
            return " " ;
        }
        else if (mExponent == 0)
        {
            return (mCoefficient >= 0 ? " + " : " - ") + Math.abs(mCoefficient) ;
        }
        //for the positive negative
        return (mCoefficient >= 0 ? " + " : " - ") + (Math.abs(mCoefficient) == 1 ? "" : ("" + Math.abs(mCoefficient))) + "x" + (Math.abs(mExponent) == 1 ? ("" ) : ("^" + mExponent)) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;
        Term term = (Term) o;
        return mExponent == term.mExponent && mCoefficient == term.mCoefficient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mExponent, mCoefficient);
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof Term)
        {
            Term temp = (Term) o ;

            return mExponent - (temp.mExponent);
        }

        return -1;
    }
}
