package org.example.feature.contactList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.example.domain.contacts.model.ContactDomain
import org.example.feature.contactList.contract.ContactListEvents
import org.example.feature.contactList.contract.ContactListEvents.OnContactClick
import org.example.utils.designSystem.AppThemeForPreview

@Composable
internal fun ContactItem(
    item: ContactDomain,
    processEvent: (ContactListEvents) -> Unit,
) = with(item) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { processEvent(OnContactClick(item.id)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = thumbnailUrl,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)),
            contentDescription = null,
        )
        Text(
            text = "$lastName $firstName",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun ContactItemPreview() = AppThemeForPreview {
    ContactItem(processEvent = { }, item = ContactDomain.Sample)
}