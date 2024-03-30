package com.kongjak.koreatechboard.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kongjak.koreatechboard.R

@Composable
fun TextFieldDialog(
    title: String,
    label: String = "",
    onConfirmString: String = stringResource(id = R.string.dialog_confirm),
    onDismissString: String = stringResource(id = R.string.dialog_dismiss),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicDialog(
        title = title,
        onConfirmString = onConfirmString,
        onDismissString = onDismissString,
        onConfirm = { onConfirm() },
        onDismiss = { onDismiss() }
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(bottom = 8.dp),
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onConfirm()
                }
            )
        )
    }
}
