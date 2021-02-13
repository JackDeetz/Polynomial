import java.util.*;

public class Polynomial
{
    private LinkedList<Term> mList ;

    public Polynomial()
    {
        mList = new LinkedList<Term>() ;
    }
    public Polynomial(Polynomial originalToCopy)
    {
        mList = new LinkedList<Term>() ;
        mList.addAll(originalToCopy.mList) ;
    }
    public int getNumTerms()
    {
        return mList.size() ;
    }
    public boolean addTerm(Term termToAdd)
    {
        for(int i = 0 ; i < mList.size() ; i++)
        {
            if (mList.get(i).compareTo(termToAdd) == 0) //works
            {
                //System.out.println("Exponents Match\noTerm=" + mList.get(i) + "\nnTerm=" + termToAdd );
                mList.get(i).setCoefficient( mList.get(i).getCoefficient() + termToAdd.getCoefficient());
                if (mList.get(i).getCoefficient() == 0)
                {
                    mList.remove(mList.get(i)) ;
                }
                //System.out.println("oTermAfterAdd=" + mList.get(i)) ;
                return true ;
            }
            else if ( mList.get(i).compareTo(termToAdd) < 0 )
            {
                mList.add(i, termToAdd) ;
                return true ;
            }
        }
        mList.addLast(termToAdd) ;

        return true ;
        //mList.add(termToAdd) ;
        //call sort/combineTerms method
        //return true ;
    }
    public Term getTerm(int indexOfTermToReturn)
    {
        return mList.get(indexOfTermToReturn) ;
    }
    public boolean add(Polynomial otherToAddFrom)
    {
        for(Term term : otherToAddFrom.mList)
        {
            this.addTerm(term) ;
        }
        return true ;
    }
    public boolean clear()
    {
        mList.clear();
        return true ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial)) return false;
        Polynomial that = (Polynomial) o;
        return Objects.equals(mList, that.mList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mList);
    }
    @Override
    public String toString()
    {
        if (mList.size() == 0)
            return "0" ;
        String output = (getTerm(0).toString()).substring(2) ;
        if (mList.size() == 1)
            return output ;
        for (int i = 1 ; i < mList.size() ; i++)
        {
            output += mList.get(i) ;
        }
        return output ;
    }

}
