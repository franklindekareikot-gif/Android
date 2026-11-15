package com.example.androidlab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LabRed = Color(0xFFC62828)

data class TaskItem(val id: Int, val name: String, val done: Boolean)

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TaskScreen() }
    }
}

@Composable
fun TaskScreen() {
    var tasks by remember {
        mutableStateOf(
            listOf(
                TaskItem(1, "学习 Column 和 Row", true),
                TaskItem(2, "学习状态管理", false),
                TaskItem(3, "完成 Compose 实验", false)
            )
        )
    }
    var input by remember { mutableStateOf("") }
    var nextId by remember { mutableIntStateOf(4) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "课程学习任务",
            color = LabRed,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                placeholder = { Text("请输入学习任务", fontSize = 13.sp) }
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        tasks = tasks + TaskItem(nextId++, input.trim(), false)
                        input = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = LabRed)
            ) {
                Text("添加")
            }
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = "已完成: ${tasks.count { it.done }} / ${tasks.size}",
            fontSize = 13.sp,
            color = Color(0xFF666666)
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks, key = { it.id }) { task ->
                TaskRow(
                    task = task,
                    onToggle = {
                        tasks = tasks.map {
                            if (it.id == task.id) it.copy(done = !it.done) else it
                        }
                    },
                    onDelete = { tasks = tasks.filterNot { it.id == task.id } }
                )
            }
        }
    }
}

@Composable
private fun TaskRow(task: TaskItem, onToggle: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.done, onCheckedChange = { onToggle() })
            Text(
                text = task.name,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None,
                color = if (task.done) Color(0xFF999999) else Color(0xFF333333)
            )
            Text(
                text = "删除",
                color = LabRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDelete() }
            )
        }
    }
}
