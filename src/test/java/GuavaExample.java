import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;

import org.hamcrest.core.*;
import org.junit.*;
import org.junit.experimental.runners.*;
import org.junit.runner.*;

import com.google.common.base.*;

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
}
