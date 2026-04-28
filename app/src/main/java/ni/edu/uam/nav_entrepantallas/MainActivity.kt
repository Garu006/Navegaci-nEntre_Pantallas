package ni.edu.uam.nav_entrepantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.mutableIntSetOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ni.edu.uam.nav_entrepantallas.ui.theme.Nav_EntrePantallasTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.autofill.contentType
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nav_EntrePantallasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            PantallaInicio(navController)
        }

        composable("detalle") {
            PantallaDetalle(navController)
        }

        composable("perfil") {
            PantallaPerfil(navController)
        }
    }
}

@Composable
fun PantallaInicio(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FitUAM",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.rutina_fuerza),
                contentDescription = "Rutina de fuerza",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Rutinas personalizadas para mejorar tu condición fisica.")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("detalle")
                }
            ) {
                Text("Ver ejercicio")
            }
        }
    }
}

@Composable
    fun PantallaDetalle(navController: NavHostController) {
        val imagenes = listOf(
            R.drawable.ejercicio_sentadilla,
            R.drawable.rutina_fuerza
        )
        var imagenActual by remember {
            mutableIntStateOf(0)
        }
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Ejercicio: Sentadilla",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = imagenes[imagenActual]),
                    contentDescription = "Imagen del ejercicio",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Instrucciones",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Series recomendadas: 4")
                Text("Repeticiones: 12")
                Text("Descanso: 60 segundos")

                Button(
                    onClick = {
                        imagenActual = (imagenActual + 1) % imagenes.size
                    }
                ) {
                    Text("Cambiar imagen")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate("perfil")
                    }
                ) {
                    Text("Ver progreso")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ){
                    Text("Volver")
                }
            }
        }
}

@Composable
fun PantallaPerfil(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Perfil FitUAM",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.perfil_gym),
                contentDescription = "Perfil de entrenamiento",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Resumen del usuario",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Nombre: Gabriel")
            Text("Nivel: Principiante")
            Text("Rutinas completadas: 8")
            Text("Objetivo: Mejorar fuerza y resistencia")

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Progreso semanal: 70%",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(18.dp)
                        .background(
                            color = Color(0xFF4CAF50),
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("inicio") {
                        popUpTo("inicio") {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text("Volver al Inicio")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Volver a Detalle")
            }
        }
    }
}