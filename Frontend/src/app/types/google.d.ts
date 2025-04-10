declare global {
    interface Window {
      google: any;
      onGoogleLibraryLoad: () => void;
    }
    const google: any;
  }
  
  export {};