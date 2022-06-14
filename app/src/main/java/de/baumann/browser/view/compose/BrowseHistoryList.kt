package de.baumann.browser.view.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import de.baumann.browser.database.Record
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.appcompattheme.AppCompatTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BrowseHistoryList(records: List<Record>, onClick: (Int)->Unit, onLongClick: (Int)->Unit) {
    LazyColumn {
        items(records.size) { index ->
            RecordItem(
                record = records[index],
                modifier = Modifier.combinedClickable (
                    onClick = { onClick(index) },
                    onLongClick = { onLongClick(index) }
                )
            )
        }
    }
}

@Composable
fun RecordItem(
    modifier: Modifier,
    record: Record
) {
    val timeString = SimpleDateFormat("MMM dd", Locale.getDefault()).format(record.time)
    Row(
        modifier = modifier.height(54.dp).padding(5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(1F).align(Alignment.CenterVertically),
            text = record.title ?: "Unknown",
            fontSize = 21.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier.wrapContentSize().align(Alignment.CenterVertically),
            text = timeString
        )
    }
}

@Preview
@Composable
private fun previewItem() {
    AppCompatTheme {
        RecordItem(
            modifier = Modifier,
            record = Record(title = "Hello", url = "123", time = System.currentTimeMillis())
        )
    }
}


@Preview
@Composable
private fun previewHistoryList() {
    val list = listOf(
        Record(title = "Hello aaa aaa aaa aa aa aaa aa a aa a a a aa a a a a a a a a a aa a a ", url = "123", time = System.currentTimeMillis()),
        Record(title = "Hello 2", url = "123", time = System.currentTimeMillis()),
        Record(title = "Hello 3", url = "123", time = System.currentTimeMillis()),
    )
    AppCompatTheme {
        BrowseHistoryList(records = list, {}, {})
    }
}