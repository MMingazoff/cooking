package ru.itis.cooking.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.cooking.core.ui.theme.LightColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    isEnable: Boolean,
    title: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isEnable,
                onCheckedChange = { onClick() },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.onSecondary,
                    uncheckedColor = LightColor
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}
