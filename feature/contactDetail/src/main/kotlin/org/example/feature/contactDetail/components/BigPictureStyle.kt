package org.example.feature.contactDetail.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.forwardingPainter.forwardingPainter
import org.example.utils.sharedResources.R

@Composable
internal fun BigPictureStyle(
    pictureUrl: String
) {
    val placeHolder = forwardingPainter(
        painter = painterResource(id = R.drawable.place_holder),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
    AsyncImage(
        model = pictureUrl,
        modifier = Modifier
            .height(300.dp)
            .aspectRatio(.85f)
            .clip(RoundedCornerShape(topEnd = 40.dp, bottomEnd = 40.dp)),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = placeHolder,
        error = placeHolder,
    )
}

@Preview
@Composable
private fun BigPictureStylePreview() = AppThemeForPreview {
    BigPictureStyle(pictureUrl = "https://picsum.photos/200/300")
}