// paypal.d.ts
declare global {
    interface Window {
      paypal?: {
        Buttons: (options: {
          createOrder: (data: any, actions: any) => Promise<string>;
          onApprove: (data: any, actions: any) => Promise<void>;
          onError: (err: any) => void;
          style?: {
            layout?: 'vertical' | 'horizontal';
            color?: 'gold' | 'blue' | 'silver' | 'white' | 'black';
            shape?: 'pill' | 'rect';
            label?: 'paypal' | 'checkout' | 'pay' | 'buynow';
          };
        }) => {
          render: (container: HTMLElement) => void;
        };
      };
    }
  }
  
  export {};