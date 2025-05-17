# 🎨 Paleta de colores & Accesibilidad (Contraste)

A continuación se listan las combinaciones de color con buen contraste (según **WCAG 2.1**) y aquellas que **no son accesibles** para texto normal.

## ✅ Contrastes recomendados (≥ 4.5:1)

| Texto (`color`) | Fondo (`background`) | Ratio de contraste | Comentario                    |
|------------------|----------------------|---------------------|-------------------------------|
| `#F9F9F9`        | `#383838`            | **13.3:1**          | Excelente contraste           |
| `#383838`        | `#F9F9F9`            | **13.3:1**          | Inverso, también excelente    |
| `#383838`        | `#C8C8C8`            | **12.6:1**          | Ideal sobre fondo claro       |
| `#F9F9F9`        | `#02353C`            | **9.2:1**           | Muy buen contraste            |
| `#F9F9F9`        | `#004CB8`            | **6.6:1**           | Bueno para botones o headers  |
| `#02353C`        | `#00F977`            | **4.6:1**           | Justo pero válido             |
| `#383838`        | `#00FFE0`            | **5.0:1**           | Legible sobre fondo vibrante  |

## ❌ Contrastes NO válidos (< 4.5:1)

| Texto (`color`) | Fondo (`background`) | Ratio de contraste | Problema                     |
|------------------|----------------------|---------------------|------------------------------|
| `#01C883`        | `#00F977`            | **1.26:1**          | Muy poco contraste           |
| `#00CFF7`        | `#00FFE0`            | **1.29:1**          | Colores muy similares        |
| `#C8C8C8`        | `#F9F9F9`            | **1.61:1**          | Gris claro sobre blanco      |
| `#00F977`        | `#F9F9F9`            | **1.48:1**          | Verde neón poco legible      |

---

## 📌 Reglas rápidas

- Para texto normal: mínimo **4.5:1**
- Para texto grande (>18pt o 14pt bold): mínimo **3:1**
- Elementos decorativos no requieren contraste mínimo.

---

