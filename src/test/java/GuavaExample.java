import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;

import org.junit.*;
import org.junit.experimental.runners.*;
import org.junit.runner.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.escape.*;
import com.google.common.html.*;
import com.google.common.primitives.*;

@RunWith(Enclosed.class)
public class GuavaExample {

    public static class SplitterTest {
        
        private String target = ",a,,b,"; 
        
        @Test
        public void split() {
            Iterator<String> actual = Splitter.on(',').split(target).iterator();
            assertThat(actual.next(), is(""));
            assertThat(actual.next(), is("a"));
            assertThat(actual.next(), is(""));
            assertThat(actual.next(), is("b"));
            assertThat(actual.next(), is(""));
        }
        
        @Test
        public void omitEmptyString() {
            Iterator<String> actual = Splitter.on(',')
                    .omitEmptyStrings()
                    .split(target)
                    .iterator();
            assertThat(actual.next(), is("a"));
            assertThat(actual.next(), is("b"));
        }
        
        @Test
        public void splitToList() {
            List<String> actual = Splitter.on(',').splitToList("a,b,c,d,e");
            assertThat(actual.size(), is(5));
            assertThat(actual.get(0), is("a"));
            assertThat(actual.get(1), is("b"));
            assertThat(actual.get(2), is("c"));
            assertThat(actual.get(3), is("d"));
            assertThat(actual.get(4), is("e"));
        }
    }
    
    public static class StringsTest {
        
        @Test
        public void isNullOrEmpty() {
            String nullStr = null;
            assertThat(Strings.isNullOrEmpty(nullStr), is(true));
            
            String emptyStr = "";
            assertThat(Strings.isNullOrEmpty(emptyStr), is(true));
            
            String notEmptyStr = "hello";
            assertThat(Strings.isNullOrEmpty(notEmptyStr), is(false));
        }
        
        @Test
        public void repeat() {
            String actual = Strings.repeat("=", 3);
            String expected = "===";
            assertThat(actual, is(expected));
        }
        
        @Test
        public void emptyToNull() {
            assertThat(Strings.emptyToNull(""), is(nullValue()));
        }
        
        @Test
        public void nullToEmpty() {
            assertThat(Strings.nullToEmpty(null), is(""));
        }
        
        @Test
        public void commonSuffix() {
            String a = "aaa.txt";
            String b = "bbb.txt";
            assertThat(Strings.commonSuffix(a, b), is(".txt"));
        }
        
        @Test
        public void commonPrefix() {
            String a = "test.py";
            String b = "test.rb";
            assertThat(Strings.commonPrefix(a, b), is("test."));
        }
        
        @Test
        public void padEnd() {
            String actual = Strings.padEnd("123", 5, '0');
            String expected = "12300";
            assertThat(actual, is(expected));
        }
        
        @Test
        public void padStart() {
            String actual = Strings.padStart("xxxx", 10, '_');
            String expected = "______xxxx";
            assertThat(actual, is(expected));
        }
    }
    
    public static class HtmlEscapersTest {
        
        private Escaper escaper = HtmlEscapers.htmlEscaper();
        
        @Test
        public void ampersand() {
            String actual = escaper.escape("&");
            String expected = "&amp;";
            assertThat(actual, is(expected));
        }
        
        @Test
        public void lessThan() {
            String actual = escaper.escape("<");
            String expected = "&lt;";
            assertThat(actual, is(expected));
        }
        
        @Test
        public void greaterThan() {
            String actual = escaper.escape(">");
            String expected = "&gt;";
            assertThat(actual, is(expected)); 
        }
        
        @Test
        public void quote() {
            String actual = escaper.escape("\'");
            String expected = "&#39;";
            assertThat(actual, is(expected));
        }
        
        @Test
        public void wquote() {
            String actual = escaper.escape("\"");
            String expected = "&quot;";
            assertThat(actual, is(expected));
            
        }
    }
    
    public static class IntsTest {
        
        @Test
        public void asList() {
            List<Integer> actual = Ints.asList(1,3,5,7,9);
            assertThat(actual.size(), is(5));
            assertThat(actual.get(0), is(1));
            assertThat(actual.get(1), is(3));
            assertThat(actual.get(2), is(5));
            assertThat(actual.get(3), is(7));
            assertThat(actual.get(4), is(9));
        }
        
        @Test
        public void concat() {
            int[] array1 = new int[]{1,2,3};
            int[] array2 = new int[]{4,5,6,7};
            int[] actual = Ints.concat(array1, array2);
            assertThat(actual.length, is(array1.length + array2.length));
            assertThat(actual[0], is(array1[0]));
            assertThat(actual[1], is(array1[1]));
            assertThat(actual[2], is(array1[2]));
            assertThat(actual[3], is(array2[0]));
            assertThat(actual[4], is(array2[1]));
            assertThat(actual[5], is(array2[2]));
            assertThat(actual[6], is(array2[3]));
        }
        
        @Test
        public void toArray() {
            List<Integer> intList = new ArrayList<>();
            intList.add(10);
            intList.add(20);
            intList.add(30);
            int[] actual = Ints.toArray(intList);
            assertThat(actual.length, is(3));
            assertThat(actual[0], is(10));
            assertThat(actual[1], is(20));
            assertThat(actual[2], is(30));
        }
        
        @Test
        public void min(){
            assertThat(Ints.min(10, 3, 3, 65, 34), is(3));
        }
        
        @Test
        public void max() {
            assertThat(Ints.max(1, 3, 4, 9384, 343), is(9384));
        }
    }
    
    public static class ImmutableListTest {
        
        @Test(expected=UnsupportedOperationException.class)
        public void add() {
            List<String> list = ImmutableList.of("foo", "baa", "baz");
            list.add("hoge");
        }
        
        @Test(expected=UnsupportedOperationException.class)
        public void addAll() {
            List<String> list = ImmutableList.of("foo", "baa", "baz");
            list.addAll(Arrays.asList("hoge", "fuga"));
        }
        
        @Test(expected=UnsupportedOperationException.class)
        public void remove() {
            List<String> list = ImmutableList.of("foo", "baa", "baz");
            list.remove(0);
        }
        
        @Test(expected=NullPointerException.class)
        public void of_doesNotPermitNull() {
            ImmutableList.of("foo", "baa", "baz", null);
        }
        
        @Test
        public void copyOf () {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            List<String> copyList = ImmutableList.copyOf(list);
            assertThat(list, is(not(sameInstance(copyList))));
            assertThat(list.get(0), is(copyList.get(0)));
            assertThat(list.get(1), is(copyList.get(1)));
            assertThat(list.get(2), is(copyList.get(2)));
        }
    }
}
