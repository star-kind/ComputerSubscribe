/*
web-vitals是 Google 发起的,旨在提供各种质量信号的统一指南, Google 相信这些质量信号对提供出色的网络用户体验至关重要. 
其可获取三个关键指标(CLS、FID、LCP)和两个辅助指标(FCP、TTFB). 这些指标的含义可以查看后面的参考文档
*/
const reportWebVitals = onPerfEntry => {
  if (onPerfEntry && onPerfEntry instanceof Function) {
    import('web-vitals').then(({ getCLS, getFID, getFCP, getLCP, getTTFB }) => {
      getCLS(onPerfEntry);
      getFID(onPerfEntry);
      getFCP(onPerfEntry);
      getLCP(onPerfEntry);
      getTTFB(onPerfEntry);
    });
  }
};

export default reportWebVitals;
