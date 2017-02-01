package dataDictionary;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/1/30 下午10:00
 */
public enum EntityType {

    ENTITY_NEWS("ENTITY_NEWS",1), ENTITY_COMMENT("ENTITY_COMMENT",2);

    private String name;

    private int index;

    EntityType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getindex() {
        return index;
    }

    public void setindex(int index) {
        this.index = index;
    }
}
