package ReckonObjects;

/**
 * Created by nirav on 1/5/17.
 */
public class AllBanks {
    private Banks[] banks;

    public Banks[] getBanks ()
    {
        return banks;
    }

    public void setBanks (Banks[] banks)
    {
        this.banks = banks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [banks = "+banks+"]";
    }
}
