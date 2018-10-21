package graphiks;

public class ProjectData {
    public int getM_id() {
        return m_id;
    }

    private int m_id;

    public String getM_name() {
        return m_name;
    }

    private String m_name;

    public Date getM_lastopened() {
        return m_lastopened;
    }

    public void setM_lastopened(Date m_lastopened) {
        this.m_lastopened = m_lastopened;
    }

    private Date m_lastopened;

    public String getM_formulas() {
        return m_formulas;
    }

    public void setM_formulas(String m_formulas) {
        this.m_formulas = m_formulas;
    }

    private String m_formulas;

    public ProjectData(int id, String name, int lastopened, String formulas){
        m_formulas = formulas;
        m_id = id;
        m_lastopened = Date.decodeInteger(lastopened);
        m_name = name;
    }
}
