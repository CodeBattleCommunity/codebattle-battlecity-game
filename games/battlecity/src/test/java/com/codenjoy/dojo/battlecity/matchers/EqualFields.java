package com.codenjoy.dojo.battlecity.matchers;

import org.hamcrest.Description;
import org.hamcrest.core.IsEqual;

public class EqualFields<T> extends IsEqual<T> {
    private T expectedValue;

    public EqualFields(T gotValue) {
        super(gotValue);
        this.expectedValue = gotValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("\n" + expectedValue.toString());
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("was \n").appendText(item.toString());
    }
}
