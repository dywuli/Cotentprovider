package net.simonvt.schematic.sample.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;
import net.simonvt.schematic.sample.BuildConfig;

@TableEndpoint(table = NotesDatabase.Tables.NOTES_TAGS,
        packageName = "net.simonvt.schematic.sample.provider")
public class NotesTags {
    @InexactContentUri(
            name = "TAGS_FOR_NOTE",
            path = NotesProvider.Path.NOTES + "/#/" + NotesProvider.Path.TAGS,
            type = "vnd.android.cursor.dir/note/tags",
            whereColumn = TagColumns.NOTE_ID,
            pathSegment = 1
    )
    public static Uri fromNote(long noteId) {
        return buildUri(NotesProvider.Path.NOTES, String.valueOf(noteId), NotesProvider.Path.TAGS);
    }
    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }
    public static final String AUTHORITY = BuildConfig.PROVIDER_AUTHORITY;

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
}
