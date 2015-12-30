import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 验证测试Map的有序性
 * 结论：HashMap是无法保证元素的序列的，如果要保证元素的插入序列，使用LinkedHashMap
 * @author Jeby Sun
 * 2015-12-29 下午3:27:56
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i=1; i<=20; i++) {
			map.put("key"+i, "value"+i);
		}
		
		printByEntrySet(map);
	}
	
	/**
	 * 遍历打印Map
	 * 这是最常见的并且在大多数情况下也是最可取的遍历方式。在键值都需要时使用。
	 * @param map
	 */
    public static void printByEntrySet(Map<String, String> map) {
    	Set<Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {  
            System.out.println(entry.getKey() + "=" + entry.getValue());  
        }  
    }
    
    /**
     * 遍历打印Map
     * 效率很低，不建议用
     * @author Jeby Sun
     */
    public static void printByKeySet(Map<String, String> map) {
    	for (String key : map.keySet()) {
    		String value = map.get(key);
    		System.out.println(key + "=" + value);
    	}
    }

}
