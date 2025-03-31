# 💳 SmartPosSdk

Aplicativo Android modular e escalável desenvolvido com Kotlin, que simula o fluxo de pagamento com teclado numérico personalizado e integração com o Mercado Pago (ambiente de teste).  
Feito com foco em boas práticas de arquitetura, usabilidade e organização de código.

---

## 📱 Funcionalidades

- Digitação de valores com formatação monetária (R$)
- Descrição opcional do produto
- Criação de link de pagamento via API Mercado Pago (sandbox)
- Exibição de alerta com opção de abrir o link ou copiar para área de transferência
- Splash screen animada
- Suporte a diferentes tamanhos de tela

---

## ▶️ Como rodar o projeto

### ✅ Pré-requisitos

- Android Studio (Electric Eel ou superior)
- Gradle 8.x
- Java 17
- Conta no [Mercado Pago Developers](https://www.mercadopago.com.br/developers/)

### 🔧 Passos

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/SmartPosSdk.git


🧱 Estrutura do Projeto

com.example.smartpossdk
├── data                 # Camada de dados (modelos, API, repositórios)
│   ├── model
│   ├── remote
│   └── repository
├── domain              # Regras de negócio (interface e use cases)
│   ├── repository
│   └── usecase
├── presentation        # Camada de interface (UI, ViewModel, componentes)
│   ├── activity
│   ├── fragment
│   ├── viewmodel
│   ├── state
│   └── components
├── di                  # Módulos de injeção com Hilt
├── utils               # Formatadores, extensões
├── res/                # Layouts, strings, dimens, cores, estilos
└── SmartPosSdkApp.kt   # Classe Application com Hilt

![image](https://github.com/user-attachments/assets/079e846b-605b-4259-b429-76cd216b4312)


// Core
implementation("androidx.core:core-ktx:1.12.0")
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("com.google.android.material:material:1.10.0")

// Lifecycle & ViewModel
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

// Navigation
implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

// Retrofit + Gson
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.squareup.okhttp3:okhttp:4.11.0")

// Hilt
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-android-compiler:2.48")

// Kotlin Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// ViewBinding
buildFeatures {
    viewBinding true
}


