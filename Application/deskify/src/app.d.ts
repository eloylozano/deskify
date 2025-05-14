// src/app.d.ts
declare global {
    namespace App {
        interface Locals {
            user?: {
                id: number;
                email: string;
                firstName: string;
                lastName: string;
                role: {
                    id: number;
                    name: string;
                };
            };
        }

        interface PageData {
            user?: Locals['user'];
        }
    }
}

export {};