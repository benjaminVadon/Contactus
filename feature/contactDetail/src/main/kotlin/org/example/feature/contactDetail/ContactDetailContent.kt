package org.example.feature.contactDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.example.domain.contacts.model.ContactDomain
import org.example.feature.contactDetail.contract.ContactDetailEvents
import org.example.feature.contactDetail.contract.ContactDetailState
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun ContactDetailContent(
    state: ContactDetailState,
    processEvent: (ContactDetailEvents) -> Unit,
) {
    Scaffold { paddingValues ->

        state.contact?.run {
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 20.dp,
                        end = 16.dp,
                        bottom = 40.dp,
                    ),
                    text = "$lastName $firstName",
                    style = MaterialTheme.typography.displayMedium,
                    minLines = 2,
                    maxLines = 2
                )
                Row {
                    AsyncImage(
                        model = pictureUrl,
                        modifier = Modifier
                            .height(300.dp)
                            .aspectRatio(.85f)
                            .clip(RoundedCornerShape(topEnd = 40.dp, bottomEnd = 40.dp)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .align(CenterVertically),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Block(R.string.age, age.toString())
                        Block(R.string.gender, gender)
                        Block(R.string.nationality, nationality)
                    }
                }
            }
        }
    }
}

@Composable
internal fun Block(topTextResId: Int, bottomText: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = bottomText,
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = stringResource(id = topTextResId),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview
@Composable
private fun ContactDetailContentPreview() = AppThemeForPreview {
    ContactDetailContent(
        state = ContactDetailState(contact = ContactDomain.Sample),
        processEvent = { }
    )
}