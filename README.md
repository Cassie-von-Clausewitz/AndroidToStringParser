This little library parses the output of the Android View.toString() method. As we can see from the implementation
of `toString` on the `View` class we can see that the method returns some interesting info from it's `toString` override

```java
public class View {
    public String toString() {
        StringBuilder out = new StringBuilder(128);
        out.append(getClass().getName());
        out.append('{');
        out.append(Integer.toHexString(System.identityHashCode(this)));
        out.append(' ');
        switch (mViewFlags&VISIBILITY_MASK) {
            case VISIBLE: out.append('V'); break;
            case INVISIBLE: out.append('I'); break;
            case GONE: out.append('G'); break;
            default: out.append('.'); break;
        }
        out.append((mViewFlags & FOCUSABLE) == FOCUSABLE ? 'F' : '.');
        out.append((mViewFlags&ENABLED_MASK) == ENABLED ? 'E' : '.');
        out.append((mViewFlags&DRAW_MASK) == WILL_NOT_DRAW ? '.' : 'D');
        out.append((mViewFlags&SCROLLBARS_HORIZONTAL) != 0 ? 'H' : '.');
        out.append((mViewFlags&SCROLLBARS_VERTICAL) != 0 ? 'V' : '.');
        out.append((mViewFlags&CLICKABLE) != 0 ? 'C' : '.');
        out.append((mViewFlags&LONG_CLICKABLE) != 0 ? 'L' : '.');
        out.append((mViewFlags&CONTEXT_CLICKABLE) != 0 ? 'X' : '.');
        out.append(' ');
        out.append((mPrivateFlags&PFLAG_IS_ROOT_NAMESPACE) != 0 ? 'R' : '.');
        out.append((mPrivateFlags&PFLAG_FOCUSED) != 0 ? 'F' : '.');
        out.append((mPrivateFlags&PFLAG_SELECTED) != 0 ? 'S' : '.');
        if ((mPrivateFlags&PFLAG_PREPRESSED) != 0) {
            out.append('p');
        } else {
            out.append((mPrivateFlags&PFLAG_PRESSED) != 0 ? 'P' : '.');
        }
        out.append((mPrivateFlags&PFLAG_HOVERED) != 0 ? 'H' : '.');
        out.append((mPrivateFlags&PFLAG_ACTIVATED) != 0 ? 'A' : '.');
        out.append((mPrivateFlags&PFLAG_INVALIDATED) != 0 ? 'I' : '.');
        out.append((mPrivateFlags&PFLAG_DIRTY_MASK) != 0 ? 'D' : '.');
        out.append(' ');
        out.append(mLeft);
        out.append(',');
        out.append(mTop);
        out.append('-');
        out.append(mRight);
        out.append(',');
        out.append(mBottom);
        final int id = getId();
        if (id != NO_ID) {
            out.append(" #");
            out.append(Integer.toHexString(id));
            final Resources r = mResources;
            if (id > 0 && Resources.resourceHasPackage(id) && r != null) {
                try {
                    String pkgname;
                    switch (id&0xff000000) {
                        case 0x7f000000:
                            pkgname="app";
                            break;
                        case 0x01000000:
                            pkgname="android";
                            break;
                        default:
                            pkgname = r.getResourcePackageName(id);
                            break;
                    }
                    String typename = r.getResourceTypeName(id);
                    String entryname = r.getResourceEntryName(id);
                    out.append(" ");
                    out.append(pkgname);
                    out.append(":");
                    out.append(typename);
                    out.append("/");
                    out.append(entryname);
                } catch (Resources.NotFoundException e) {
                }
            }
        }
        out.append("}");
        return out.toString();
    }
}
```

We can use this knowledge to parse something like this...

`android.support.design.widget.CoordinatorLayout{9ef9382 V.E...... .......D 0,0-1080,1731}`

...into this

```json
{
        "View": "android.support.design.widget.CoordinatorLayout",
        "HashCode": "9ef9382",
        "Rect": {
            "Left": 0,
            "Top": 0,
            "Right": 1080,
            "Bottom": 1731
        },
        "Flags": {
            "Visible": "Visible",
            "Focusable": false,
            "Enabled": true,
            "Will Draw": true,
            "Showing Horizontal Scrollbars": false,
            "Showing Vertical Scrollbars": false,
            "Clickable": false,
            "Long Clickable": false,
            "Context Clickable": false,
            "Is Root Namespace": false,
            "Focused": false,
            "Selected": false,
            "Pressed": "Neither",
            "Hovered": false,
            "Activated": false,
            "Invalidated": false,
            "Is Dirty": true
        }
}
```

library coming soon
